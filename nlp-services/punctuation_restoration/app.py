from flask import Flask, request
from punctuator import Punctuator

model = Punctuator('model/Europarl-EN.pcl')

app = Flask(__name__)

@app.route("/punctuate", methods=["POST"])
def restore():
    return {'restoredText': model.punctuate(request.json['text'])}

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=86)