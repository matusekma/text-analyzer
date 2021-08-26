from flask import Flask, request
import yake

app = Flask(__name__)

DE = 'de'
EN = 'en'

max_ngram_size = 3
deduplication_thresold = 0.9
deduplication_algo = 'seqm'
windowSize = 1
numOfKeywords = 20


@app.route("/extract/english", methods=["POST"])
def extract_english():
    model = get_model(EN)
    return apply_model(model, request.json['text'])


@app.route("/extract/german", methods=["POST"])
def extract_german():
    model = get_model(DE)
    return apply_model(model, request.json['text'])


def get_model(language):
    return yake.KeywordExtractor(
        lan=language,
        n=max_ngram_size,
        dedupLim=deduplication_thresold,
        dedupFunc=deduplication_algo,
        windowsSize=windowSize,
        top=numOfKeywords,
        features=None)


def apply_model(model, text):
    keywords = model.extract_keywords(request.json['text'])
    return {"keywords": [keyword for (keyword, score) in keywords]}


if __name__ == "__main__":
    app.run(host='0.0.0.0', port=80)
