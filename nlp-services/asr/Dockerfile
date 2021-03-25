FROM python:3.8-slim
WORKDIR /app
COPY . /app
RUN pip install -r requirements.txt
RUN ls -l
EXPOSE 80
CMD ["python", "app.py"]
