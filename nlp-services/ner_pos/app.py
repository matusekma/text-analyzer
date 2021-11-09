
from flask import Flask, request
import spacy

app = Flask(__name__)

nlp_en = spacy.load("en_core_web_sm")
nlp_de = spacy.load("de_core_news_sm")

def run_ner(text, nlp):
    doc = nlp(text)
    return [{'text': entity.text, 'label': entity.label_ } for entity in doc.ents]

def run_pos(text, nlp):
    doc = nlp(text)
    return [{'text': token.text, 'tag': token.pos_, 'lemma': token.lemma_ } for token in doc]

@app.route("/ner/english", methods=["POST"])
def ner_en():
    text = request.json['text']
    return { "responses": run_ner(text, nlp_en) }

@app.route("/ner/german", methods=["POST"])
def ner_de():
    text = request.json['text']
    return { "responses": run_ner(text, nlp_de) }

@app.route("/pos/english", methods=["POST"])
def pos_en():
    text = request.json['text']
    return { "responses": run_pos(text, nlp_en) }

@app.route("/pos/german", methods=["POST"])
def pos_de():
    text = request.json['text']
    return { "responses": run_pos(text, nlp_de) }

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=83)