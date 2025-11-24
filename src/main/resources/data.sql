# Sample Database Initialization Script
# Spring Boot lo toma automáticamente al iniciar la app

REPLACE INTO sedes (id, nombre, direccion, lat, lng)
VALUES
  (1, 'Sede Central', 'Av. Siempre Viva 742, Springfield', -34.6037, -58.3816),
  (2, 'Sucursal Norte', 'Calle Falsa 123, Rosario', -32.9587, -60.6939),
  (3, 'Sucursal Sur', 'Ruta 3 Km 25, Buenos Aires', -34.815, -58.450);

-- Agregen esto a mano, ahora veo de fixearlo
--
-- REPLACE INTO course (id, name, description, professor, branch_id, starts_at, ends_at)
-- VALUES
--   (1, 'Yoga para Principiantes', 'Introducción suave a posturas de yoga, respiración y técnicas de relajación',
--    'Profesora María López', 1, '2025-09-10 08:00:00', '2025-09-10 09:00:00'),
--   (2, 'Entrenamiento HIIT', 'Entrenamiento de intervalos de alta intensidad para fuerza y resistencia',
--    'Profesor Juan Pérez', 2, '2025-09-12 18:00:00', '2025-09-12 19:00:00'),
--   (20, 'Stretch & Relax', 'Clase de estiramiento profundo y relajación',
--    'Claudia Vega', 3, '2025-10-03 08:00:00', '2025-10-03 09:00:00');

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
)

