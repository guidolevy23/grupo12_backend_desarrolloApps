# Sample Database Initialization Script
# Spring boot lo toma automaticamente al iniciar la app

REPLACE INTO sedes (id, nombre, direccion, lat, lng)
VALUES
  (1, 'Sede Central', 'Av. Siempre Viva 742, Springfield', -34.6037, -58.3816),
  (2, 'Sucursal Norte', 'Calle Falsa 123, Rosario', -32.9587, -60.6939),
  (3, 'Sucursal Sur', 'Ruta 3 Km 25, Buenos Aires', -34.815, -58.450);

REPLACE INTO course (id, name, description, professor, starts_at, ends_at)
VALUES (
            1,
           'Yoga para Principiantes',
           'Introducción suave a posturas de yoga, respiración y técnicas de relajación',
           'Profesora María López',
           '2025-09-10 08:00:00',
           '2025-09-10 09:00:00'
       ),
       (
            2,
           'Entrenamiento HIIT',
           'Entrenamiento de intervalos de alta intensidad para fuerza y resistencia',
           'Profesor Juan Pérez',
           '2025-09-12 18:00:00',
           '2025-09-12 19:00:00'
       ),
       (
           3,
           'Pilates Fuerza del Core',
           'Enfoque en mejorar postura, flexibilidad y estabilidad del core',
           'Instructora Ana Gómez',
           '2025-09-15 17:30:00',
           '2025-09-15 18:30:00'
       ),
      (
           4,
           'Fundamentos de CrossFit',
           'Aprende los fundamentos del CrossFit con movimientos funcionales y técnicas seguras',
           'Coach Miguel Rodríguez',
           '2025-09-18 19:00:00',
           '2025-09-18 20:00:00'
       ),
        (
            5,
            'CrossFit para Avanzados',
            'Mucha pila y lineas de cal',
            'Leo Matiolli',
            '2025-09-18 19:00:00',
            '2025-09-18 20:00:00'
        ),
        (
            6,
            'CrossFit para Abuelitas',
            'Con un poco de crochet',
            'Carlos Tevez',
            '2025-09-18 19:00:00',
            '2025-09-18 20:00:00'
        );

REPLACE INTO users (id, email, password, `name`, photo_url, `role`)
VALUES
  (1, 'admin@root.com', '$2a$10$WMAB9OwhzQS8jCl8mhVHf.H0H43vWH80wNvesuaZM1kMHA2j0fNyK', 'Admin User', '', 'ADMIN');