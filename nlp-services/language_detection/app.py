from flask import Flask, request
import fasttext

app = Flask(__name__)

model = fasttext.load_model('models/lid.176.ftz')


@app.route("/detect", methods=["POST"])
def detect():
    result = model.predict(request.json['text'], k=2)
    return {"languages": [r.replace('__label__', '') for r in result[0]], "confidences": result[1].tolist()}


if __name__ == "__main__":
    app.run(host='0.0.0.0', port=82)
