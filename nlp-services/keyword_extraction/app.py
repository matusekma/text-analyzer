from flask import Flask, request
import yake

kw_extractor_en = yake.KeywordExtractor()
kw_extractor_de = yake.KeywordExtractor(lan="de")

app = Flask(__name__)

def keyword_tuples_to_keywords(tuples):
    return [{'keyword' : keyword, 'score': score} for (keyword, score) in tuples]

@app.route("/extract/english", methods=["POST"])
def extract_en():
    text = request.json['text']
    keywords = kw_extractor_en.extract_keywords(text)
    return { "results":  keyword_tuples_to_keywords(keywords)}

@app.route("/extract/german", methods=["POST"])
def extract_de():
    text = request.json['text']
    keywords = kw_extractor_de.extract_keywords(text)
    return { "results":  keyword_tuples_to_keywords(keywords)}

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=87)
