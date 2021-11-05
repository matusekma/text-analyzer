import sparknlp
from flask import Flask, request
from sparknlp.pretrained import PretrainedPipeline
import nltk
from nltk.tokenize import sent_tokenize
from germansentiment import SentimentModel
nltk.download('punkt')

spark = sparknlp.start()

print("Spark NLP version: {}".format(sparknlp.version()))
print("Apache Spark version: {}".format(spark.version))

pipeline_en = PretrainedPipeline('analyze_sentimentdl_glove_imdb', 'en')
german_model = SentimentModel() #PretrainedPipeline('classifierdl_bert_sentiment_pipeline', 'de')

app = Flask(__name__)

sentiment_dict = {
    "positive": 'pos',
    "negative": 'neg',
    "neutral": 'neu'
    }

@app.route("/analyze/german", methods=["POST"])
def analyze_de():
    responses = []
    sentences = sent_tokenize(request.json['text'], language='german')
    for sentence in sentences:
        result = german_model.predict_sentiment([sentence])
        responses.append({
            "sentence": sentence,
            "sentiment": sentiment_dict[result['labels'][0]],
            "confidence": result['confidences'][0]
        })
    
    return { "responses": responses }


@app.route("/analyze/english", methods=["POST"])
def analyze_english():
    responses = []
    sentences = sent_tokenize(request.json['text'], language='english')
    for sentence in sentences:
        result = pipeline_en.fullAnnotate(sentence)
        result = result[0]["sentiment"][0]
        sentiment = result.result
        responses.append({
            "sentence": sentence,
            "sentiment": sentiment,
            "confidence": result.metadata[sentiment]
        })
        
    return { "responses": responses }

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=80)
