from fastapi import FastAPI, HTTPException
import httpx
import time

app = FastAPI(
    title="Python FastAPI Microservices",
    description="This is a FastAPI microservice that connects to a Spring Boot application.",
    version="1.0.0"
)

SPRING_BOOT_URL = "http://localhost:8080"

@app.middleware("http")
async def log_requests(request: httpx.Request, call_next):
    start_time = time.time()
    response = await call_next(request)
    process_time = time.time() - start_time
    print(f"FastAPI Middleware: {request.method} {request.url.path} processed in {process_time:.4f} s")
    return response

@app.get("/")
async def get_message_from_spring():
    async with httpx.AsyncClient() as client:
        try:
            response = await client.get(f"{SPRING_BOOT_URL}/", timeout=5.0)
            return {
                "fastapi_status": "success",
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