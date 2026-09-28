from fastapi import FastAPI, HTTPException
import httpx

app = FastAPI(
    title="Python FastAPI Microservices",
    description="This is a FastAPI microservice that connects to a Spring Boot application.",
    version="1.0.0"
)

SPRING_BOOT_URL = "http://localhost:8080"

@app.get("/")
async def get_message_from_spring():
    async with httpx.AsyncClient() as client:
        try:
            response = await client.get(f"{SPRING_BOOT_URL}/", timeout=5.0)
            return {
                "caller": "FastAPI",
                "spring_status_code": response.status_code,
                "spring_response": response.text
            }
        except httpx.RequestError as exc:
            raise HTTPException(
                status_code=503,
                detail=f"Could not reach Spring Boot : {str(exc)}"
            )

@app.get("/api/spring-data")
async def get_spring_data():
    async with httpx.AsyncClient() as client:
        try:
            response = await client.get(f"{SPRING_BOOT_URL}/api/data", timeout=5.0)
            return {
                "source": "Spring Boot",
                "data": response.json()
            }
        except httpx.RequestError as exc:
            raise HTTPException(
                status_code=503,
                detail=f"Could not reach Spring Boot : {str(exc)}"
            )

@app.post("/api/send-to-spring")
async def send_data_to_spring(payload: dict):
    async with httpx.AsyncClient() as client:
        try:
            response = await client.post(f"{SPRING_BOOT_URL}/api/process", json=payload, timeout=5.0)
            return {
                "fastapi_status": "forwarded",
                "spring_processed_result": response.json()
            }
        except httpx.RequestError as exc:
            raise HTTPException(
                status_code=503,
                detail=f"Failed to post data to Spring Boot : {str(exc)}"
            )