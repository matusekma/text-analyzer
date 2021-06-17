FROM python:3.8-slim
WORKDIR /app
COPY . /app
RUN apt update
RUN apt-get install -y gcc
RUN apt-get install -y g++
RUN pip install -r requirements.txt
EXPOSE 80
CMD ["python", "app.py"]
