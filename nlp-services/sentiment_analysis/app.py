from flask import Flask, request
import nltk
from nltk.tokenize import sent_tokenize
from germansentiment import SentimentModel
nltk.download('punkt')

from flair.models import TextClassifier
from flair.data import Sentence

english_model = TextClassifier.load('en-sentiment')
german_model = SentimentModel()

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
    for s in sentences:
        sentence = Sentence(s)
        english_model.predict(sentence)
        result = sentence.labels[0]
        sentiment = result.value.lower()
        responses.append({
            "sentence": s,
            "sentiment": sentiment_dict[sentiment] if sentiment in sentiment_dict else 'neu',
            "confidence": result.score
        })
        
    return { "responses": responses }

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=81)
