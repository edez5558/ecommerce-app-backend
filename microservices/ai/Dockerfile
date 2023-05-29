FROM python:3.10.11-slim-buster

WORKDIR /app

COPY . .

RUN pip --no-cache-dir install -r requirements.txt

EXPOSE 5000

CMD ["python","main.py"]