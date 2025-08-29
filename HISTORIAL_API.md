# Historial API Documentation

## Overview
The Historial API provides endpoints to retrieve attendance history for gym members. This functionality allows users to view their class attendance records with optional date filtering.

## Endpoints

### 1. Get Complete Attendance History
**GET** `/api/historial/{usuarioId}`

Retrieves all attendance records for a specific user, ordered by check-in date (most recent first).

**Parameters:**
- `usuarioId` (path parameter): The ID of the user

**Response:**
```json
[
  {
    "id": 1,
    "nombreClase": "Funcional",
    "nombreSede": "Palermo",
    "fecha": "15/11/2024 18:00",
    "duracionMinutos": 60,
    "profesor": "Juan Pérez"
  }
]
```

### 2. Filter Attendance History by Date Range
**POST** `/api/historial/filtrar`

Retrieves attendance records for a specific user within a date range.

**Request Body:**
```json
{
  "usuarioId": 1,
  "fechaInicio": "2024-11-01T00:00:00Z",
  "fechaFin": "2024-11-30T23:59:59Z"
}
```

**Parameters:**
- `usuarioId`: The ID of the user
- `fechaInicio` (optional): Start date in ISO 8601 format
- `fechaFin` (optional): End date in ISO 8601 format

**Response:**
Same format as the GET endpoint.

## Error Handling

- **400 Bad Request**: Invalid user ID or date format
- **200 OK**: Successful response with attendance data

## Usage Examples

### Get all attendance history for user ID 1
```bash
curl -X GET http://localhost:8080/api/historial/1
```

### Filter attendance for November 2024
```bash
curl -X POST http://localhost:8080/api/historial/filtrar \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "fechaInicio": "2024-11-01T00:00:00Z",
    "fechaFin": "2024-11-30T23:59:59Z"
  }'
```

## Data Model

### AsistenciaResponseDTO
- `id`: Attendance record ID
- `nombreClase`: Class name (discipline)
- `nombreSede`: Gym location name
- `fecha`: Formatted date and time (dd/MM/yyyy HH:mm)
- `duracionMinutos`: Class duration in minutes
- `profesor`: Instructor name

### HistorialFilterRequestDTO
- `usuarioId`: User ID (required)
- `fechaInicio`: Start date in ISO 8601 format (optional)
- `fechaFin`: End date in ISO 8601 format (optional)
