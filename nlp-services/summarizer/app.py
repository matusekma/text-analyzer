
from flask import Flask, request
import nltk
nltk.download('punkt')
from sumy.parsers.plaintext import PlaintextParser
from sumy.nlp.tokenizers import Tokenizer
from sumy.summarizers.lsa import LsaSummarizer

app = Flask(__name__)

summarizer = LsaSummarizer()
default_length = 3

def get_summary(text, language, length):
    parser = PlaintextParser.from_string(text, Tokenizer(language))
    sentences = summarizer(parser.document, sentences_count=length)
    return [str(sentence) for sentence in sentences]

def get_length(request_data):
    return request_data['length'] if 'length' in request_data.keys() else default_length

@app.route("/summarize/english", methods=["POST"])
def summarize_en():
    text = request.json['text']
    length = get_length(request.json)
    summary = get_summary(text, 'english', length)
    return { "results":  summary}

@app.route("/summarize/german", methods=["POST"])
def summarize_de():
    text = request.json['text']
    length = get_length(request.json)
    summary = get_summary(text, 'german', length)
    return { "results":  summary}

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=85)

