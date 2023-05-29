import tensorflow as tf
import tensorflow_hub as hub
import os
import PIL
from flask import Flask, request, jsonify
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

feature_extractor_model = "https://tfhub.dev/google/imagenet/mobilenet_v3_large_075_224/feature_vector/5"
feature_extractor_layer = hub.KerasLayer(
    feature_extractor_model,
    input_shape=(224,224,3),
    trainable =False
)

model = tf.keras.Sequential([
    feature_extractor_layer,
    tf.keras.layers.Dense(128,activation='relu'),
    tf.keras.layers.Dense(1,activation='sigmoid')
]
)

model.load_weights('model_weight.h5');

@app.route('/predict',methods=['POST'])
def predict():
    url = request.json['url']

    image_url = tf.keras.utils.get_file('picture',origin=url)
    img = tf.keras.utils.load_img(image_url,target_size=(224,224))
    os.remove(image_url)
    img_array = tf.keras.utils.img_to_array(img)
    img_array = tf.expand_dims(img_array,0)

    predictions = model.predict(img_array)

    is_peluche = bool(predictions[0][0] >= 0.5)

    response = {'peluche': is_peluche}

    return jsonify(response)

if __name__ == '__main__':
    app.run(host="0.0.0.0",port=5000)