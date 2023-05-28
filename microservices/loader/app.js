const express = require('express');
const multer = require('multer');
const { BlobServiceClient } = require('@azure/storage-blob');
const fs = require('fs');
const crypto = require('crypto');
const cors = require('cors');

//require('dotenv').config();

const app = express();
const upload = multer({ dest: 'uploads/' });

app.use(cors());

const connectionString = process.env.AZURE_STORAGE_CONNECTION_STRING;
const containerName = 'images';
const blobServiceClient = BlobServiceClient.fromConnectionString(connectionString);

app.post('/upload', upload.single('image'), async (req, res) => {

  try{
    const file = req.file;

    const containerClient = blobServiceClient.getContainerClient(containerName);

    const extension = file.originalname.substring(file.originalname.lastIndexOf("."));

    let current_data = (new Date()).valueOf().toString();
    let random = Math.random().toString();

    let randomName = crypto.createHash('sha1').update(current_data + random).digest('hex');

    const blobName = randomName + extension;
    const blockBlobClient = containerClient.getBlockBlobClient(blobName);

    const stream = fs.createReadStream(file.path);
    const uploadResponse = await blockBlobClient.uploadStream(stream);

    const imageUrl = blockBlobClient.url;
    console.log('Blob uploaded successfully: ', imageUrl);

    fs.unlinkSync(file.path);
    res.json({ imageUrl });
  }catch(error){
    console.log('Error create blob, ',error);
    res.status(500).send('Error al subir la imagen');
  }

});

app.listen(3000, () => {
  console.log('Server listening on port 3000');
});