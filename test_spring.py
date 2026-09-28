import httpx

SPRING_URL = "http://localhost:8080/"

def call_spring():
    try:
        print("Connecting from Python to Spring Boot...")
        response = httpx.get(SPRING_URL)
        print("=== Python received response ===")
        print(f"Status Code : {response.status_code}")
        print(f"Response Body: {response.text}")
    except httpx.RequestError as exc:
        print(f"Connection failed: {exc}")
if __name__ == "__main__":
    call_spring()  
                    