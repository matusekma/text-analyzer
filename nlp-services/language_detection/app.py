from flask import Flask, request
import fasttext

app = Flask(__name__)

model = fasttext.load_model('models/lid.176.ftz')


@app.route("/detect", methods=["POST"])
def detect():
    result = model.predict(request.json['text'], k=2)
    return {"language": result[0][0].replace('__label__', ''), "confidence": result[1][0]}


if __name__ == "__main__":
    app.run(host='0.0.0.0', port=80)
