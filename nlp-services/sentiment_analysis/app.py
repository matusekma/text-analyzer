import sparknlp
from flask import Flask, request
from sparknlp.pretrained import PretrainedPipeline

spark = sparknlp.start()

print("Spark NLP version: {}".format(sparknlp.version()))
print("Apache Spark version: {}".format(spark.version))

pipeline = PretrainedPipeline('analyze_sentimentdl_glove_imdb', 'en')

app = Flask(__name__)


@app.route("/analyze", methods=["POST"])
def filter():
    results = pipeline.fullAnnotate(request.json['text'])
    sentiment = results[0]["sentiment"][0]
    return {
        "sentiment": sentiment.result,
        "confidence": sentiment.metadata[sentiment.result]
    }


if __name__ == "__main__":
    app.run(host='0.0.0.0', port=80)
