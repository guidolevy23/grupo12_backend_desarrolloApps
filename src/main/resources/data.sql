# Sample Database Initialization Script
# Spring Boot lo toma automáticamente al iniciar la app
START TRANSACTION;
INSERT INTO sedes (id, nombre, direccion, lat, lng)
VALUES
  (1, 'Sede Central', 'Av. Siempre Viva 742, Springfield', -34.6037, -58.3816),
  (2, 'Sucursal Norte', 'Calle Falsa 123, Rosario', -32.9587, -60.6939),
  (3, 'Sucursal Sur', 'Ruta 3 Km 25, Buenos Aires', -34.815, -58.450)
ON DUPLICATE KEY UPDATE nombre=VALUES(nombre), direccion=VALUES(direccion), lat=VALUES(lat), lng=VALUES(lng);


INSERT INTO course (id, name, description, professor, branch_id, starts_at, ends_at, notified)
VALUES
  (1, 'Yoga para Principiantes', 'Introducción suave a posturas de yoga, respiración y técnicas de relajación',
   'Profesora María López', 1, '2025-11-28 20:00:00', '2025-11-28 21:00:00',false),
  (2, 'Entrenamiento HIIT', 'Entrenamiento de intervalos de alta intensidad para fuerza y resistencia',
   'Profesor Juan Pérez', 2, '2025-11-28 21:00:00', '2025-11-28 22:00:00', false),
   (20, 'Stretch & Relax', 'Clase de estiramiento profundo y relajación',
   'Claudia Vega', 3, '2025-11-28 22:00:00', '2025-11-28 23:00:00', false)
ON DUPLICATE KEY UPDATE name=VALUES(name), description=VALUES(description), professor=VALUES(professor), branch_id=VALUES(branch_id), starts_at=VALUES(starts_at), ends_at=VALUES(ends_at);


INSERT INTO users (id, email, password, `name`, photo_url, `role`, validated)
SELECT
    1,
    'admin@root.com',
    '$2a$10$WMAB9OwhzQS8jCl8mhVHf.H0H43vWH80wNvesuaZM1kMHA2j0fNyK',
    'Admin User',
    '',
    'ADMIN',
    true
FROM DUAL
WHERE NOT EXISTS(
    SELECT * FROM users WHERE id = 1
);
COMMIT;



-- -- Agregar un turno de ejemplo y una asistencia para que aparezca en el historial
-- START TRANSACTION;
-- -- Insertar asistencia asociada al usuario admin (id=1) y al turno creado
-- INSERT INTO asistencias (id, usuario_id, reseva_id, check_in_at)
-- VALUES
--   (1, 1,  1, '2025-09-10 08:05:00'),
--   (99999, 1, 20, DATE_SUB(NOW(), INTERVAL 30 MINUTE))
-- ON DUPLICATE KEY UPDATE check_in_at=VALUES(check_in_at);
-- COMMIT;
