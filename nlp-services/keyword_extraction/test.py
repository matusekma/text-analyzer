import unittest
from app import keyword_tuples_to_keywords_dict

class TestKeywordTuplesToKeywordsDict(unittest.TestCase):

    def test_keyword_tuples_to_keywords_dict(self):
        tuple1 = ('NLP', 0.7)
        tuple2 = ('microservice', 0.3)
        keywords_scores = keyword_tuples_to_keywords_dict( (tuple1, tuple2) )
        self.assertEqual(keywords_scores[0]['keyword'], tuple1[0])
        self.assertEqual(keywords_scores[0]['score'], tuple1[1])
        self.assertEqual(keywords_scores[1]['keyword'], tuple2[0])
        self.assertEqual(keywords_scores[1]['score'], tuple2[1])


if __name__ == '__main__':
    unittest.main()