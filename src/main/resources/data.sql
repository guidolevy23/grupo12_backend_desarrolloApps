# Sample Database Initialization Script
# Spring Boot lo toma automáticamente al iniciar la app

-- Deshabilitar checks de foreign keys temporalmente
SET FOREIGN_KEY_CHECKS = 0;

-- Eliminar datos existentes en orden correcto (respetando foreign keys)
DELETE FROM asistencias;
DELETE FROM reservas;
DELETE FROM turnos;
DELETE FROM course;
DELETE FROM sedes;
DELETE FROM otp;
DELETE FROM users;

-- Rehabilitar checks de foreign keys
SET FOREIGN_KEY_CHECKS = 1;

-- Insertar sedes
REPLACE INTO sedes (id, nombre, direccion, lat, lng)
VALUES
  (1, 'Sede Central', 'Av. Siempre Viva 742, Springfield', -34.6037, -58.3816),
  (2, 'Sucursal Norte', 'Calle Falsa 123, Rosario', -32.9587, -60.6939),
  (3, 'Sucursal Sur', 'Ruta 3 Km 25, Buenos Aires', -34.815, -58.450);

-- Insertar courses con branch_id (relación a sedes)
REPLACE INTO course (id, name, description, professor, branch_id, starts_at, ends_at)
VALUES
  (1, 'Yoga para Principiantes', 'Introducción suave a posturas de yoga, respiración y técnicas de relajación',
   'Profesora María López', 1, '2025-09-10 08:00:00', '2025-09-10 09:00:00'),
  (2, 'Entrenamiento HIIT', 'Entrenamiento de intervalos de alta intensidad para fuerza y resistencia',
   'Profesor Juan Pérez', 2, '2025-09-12 18:00:00', '2025-09-12 19:00:00'),
  (20, 'Stretch & Relax', 'Clase de estiramiento profundo y relajación',
   'Claudia Vega', 3, '2025-10-03 08:00:00', '2025-10-03 09:00:00');

-- Insertar usuario admin
REPLACE INTO users (id, email, password, `name`, photo_url, `role`, validated)
VALUES
  (1, 'admin@root.com', '$2a$10$WMAB9OwhzQS8jCl8mhVHf.H0H43vWH80wNvesuaZM1kMHA2j0fNyK', 'Admin User', '', 'ADMIN', true);

-- Insertar turnos (con fechas futuras para que estén activos)
REPLACE INTO turnos (id, course_id, inicio, fin, cupo_total, cupo_disponible, estado)
VALUES
  (1, 1, '2025-11-24 13:00:00', '2025-11-24 14:00:00', 20, 15, 'ACTIVO'),
  (2, 2, '2025-11-24 14:00:00', '2025-11-24 15:00:00', 15, 10, 'ACTIVO');

-- Insertar asistencias de prueba para el usuario admin
REPLACE INTO asistencias (id, usuario_id, turno_id, course_id, check_in_at, rating, comment)
VALUES
  (1, 1, 1, 1, '2025-11-24 10:30:00', NULL, NULL),
  (2, 1, 2, 2, '2025-11-24 11:00:00', NULL, NULL);
