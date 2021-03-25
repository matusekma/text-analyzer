import wave

import deepspeech
import numpy as np

model_en = deepspeech.Model('models/deepspeech-0.9.3-models.pbmm')
model_en.enableExternalScorer('models/deepspeech-0.9.3-models.scorer')

model_de = deepspeech.Model('models/model_de.pbmm')
model_de.enableExternalScorer('models/scorer_de.scorer')

DE = 'de'
EN = 'en'

class ASRService():
    active_model = None

    def speech_to_text(self, file, lang):
        if lang == EN:
            active_model = model_en
        elif lang == DE:
            active_model = model_de
        
        with wave.open(file, 'rb') as fin:
            audio = np.frombuffer(fin.readframes(fin.getnframes()), np.int16)
            return active_model.stt(audio)
