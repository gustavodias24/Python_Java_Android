import requests

def main():
    req = requests.get("https://dog.ceo/api/breeds/image/random")
    code = req.status_code
    if  code == 200:
        return req.json()["message"]
    else:
        return f"https://http.cat/{code}"