from werkzeug.exceptions import HTTPException


class FileUploadError(HTTPException):
    code = 400

    def __init__(self, message, status_code=None, payload=None):
        HTTPException.__init__(self, message)
        self.description = message
        if status_code is not None:
            self.code = status_code
        self.payload = payload

    def to_dict(self):
        payload = dict(self.payload or ())
        return payload
        