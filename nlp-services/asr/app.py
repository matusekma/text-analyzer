from flask import Flask, json, jsonify, request

from errors.FileUploadError import FileUploadError
from service.ASRService import ASRService, EN, DE

app = Flask(__name__)
app.config['MAX_CONTENT_LENGTH'] = 16 * 1024 * 1024

ALLOWED_EXTENSIONS = {'wav'}

asr_service = ASRService()


@app.route("/asr/english", methods=["POST"])
def speech_to_text_english():
    file = validate_and_get_file(request)
    return asr_service.speech_to_text(file, EN)


@app.route("/asr/german", methods=["POST"])
def speech_to_text_german():
    file = validate_and_get_file(request)
    return asr_service.speech_to_text(file, DE)


def validate_and_get_file(req):
    if 'file' not in req.files:
        raise FileUploadError("No file part")
    file = req.files['file']
    if not file or file.filename == '':
        raise FileUploadError("No filename")
    if not allowed_file(file.filename):
        raise FileUploadError("File extension not allowed")
    return file


def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.errorhandler(FileUploadError)
def handle_file_upload_error(error):
    response = error.get_response()
    response.data = json.dumps({
        "code": error.code,
        "name": error.name,
        "description": error.description,
        "payload": error.to_dict()
    })
    response.content_type = "application/json"
    return response, 400

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=80)
