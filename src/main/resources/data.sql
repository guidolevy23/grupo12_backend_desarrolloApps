# Sample Database Initialization Script
# Spring boot lo toma automaticamente al iniciar la app

REPLACE INTO sedes (id, nombre, direccion, lat, lng)
VALUES
  (1, 'Sede Central', 'Av. Siempre Viva 742, Springfield', -34.6037, -58.3816),
  (2, 'Sucursal Norte', 'Calle Falsa 123, Rosario', -32.9587, -60.6939),
  (3, 'Sucursal Sur', 'Ruta 3 Km 25, Buenos Aires', -34.815, -58.450);

REPLACE INTO course (id, name, description, professor, branch, starts_at, ends_at)
VALUES
  (1, 'Yoga para Principiantes', 'Introducción suave a posturas de yoga, respiración y técnicas de relajación', 'Profesora María López', 'Sede Central', '2025-09-10 08:00:00', '2025-09-10 09:00:00'),
  (2, 'Entrenamiento HIIT', 'Entrenamiento de intervalos de alta intensidad para fuerza y resistencia', 'Profesor Juan Pérez', 'Sucursal Norte', '2025-09-12 18:00:00', '2025-09-12 19:00:00'),
  (3, 'Pilates Fuerza del Core', 'Enfoque en mejorar postura, flexibilidad y estabilidad del core', 'Instructora Ana Gómez', 'Sucursal Sur', '2025-09-15 17:30:00', '2025-09-15 18:30:00'),
  (4, 'Fundamentos de CrossFit', 'Aprende los fundamentos del CrossFit con movimientos funcionales y técnicas seguras', 'Coach Miguel Rodríguez', 'Sede Central', '2025-09-18 19:00:00', '2025-09-18 20:00:00'),
  (5, 'CrossFit para Avanzados', 'Mucha pila y lineas de cal', 'Leo Matiolli', 'Sucursal Norte', '2025-09-18 19:00:00', '2025-09-18 20:00:00'),
  (6, 'CrossFit para Abuelitas', 'Con un poco de crochet', 'Carlos Tevez', 'Sucursal Sur', '2025-09-18 19:00:00', '2025-09-18 20:00:00'),

  -- Más cursos
  (7, 'Zumba Night', 'Clase divertida de Zumba con música latina', 'Laura Fernández', 'Sede Central', '2025-09-20 20:00:00', '2025-09-20 21:00:00'),
  (8, 'Spinning Power', 'Sesión intensa de spinning para quemar calorías', 'Martín González', 'Sucursal Norte', '2025-09-21 07:00:00', '2025-09-21 08:00:00'),
  (9, 'Stretching y Movilidad', 'Ejercicios para mejorar la movilidad y prevenir lesiones', 'Silvia Duarte', 'Sucursal Sur', '2025-09-22 09:30:00', '2025-09-22 10:15:00'),
  (10, 'Boxeo Fitness', 'Entrenamiento de boxeo recreativo y físico', 'Jorge Ramírez', 'Sede Central', '2025-09-23 18:30:00', '2025-09-23 19:30:00'),
  (11, 'Funcional Express', 'Circuito de ejercicios funcionales en 30 minutos', 'Cecilia Torres', 'Sucursal Norte', '2025-09-24 12:00:00', '2025-09-24 12:30:00'),
  (12, 'Meditación Guiada', 'Sesión de mindfulness y meditación guiada', 'Monje Nicolás', 'Sucursal Sur', '2025-09-25 19:00:00', '2025-09-25 20:00:00'),
  (13, 'Power Yoga', 'Clase dinámica que combina yoga con ejercicios de fuerza', 'Mariana Díaz', 'Sede Central', '2025-09-26 08:00:00', '2025-09-26 09:00:00'),
  (14, 'Aqua Gym', 'Ejercicios aeróbicos en pileta', 'Pedro Sánchez', 'Sucursal Norte', '2025-09-27 10:00:00', '2025-09-27 11:00:00'),
  (15, 'Tai Chi', 'Disciplina oriental para mejorar equilibrio y energía', 'Maestro Chen', 'Sucursal Sur', '2025-09-28 08:30:00', '2025-09-28 09:30:00'),
  (16, 'Body Pump', 'Rutina de fuerza con barra y pesas', 'Nicolás Suárez', 'Sede Central', '2025-09-29 19:00:00', '2025-09-29 20:00:00'),
  (17, 'Pilates Avanzado', 'Clase de pilates para alumnos con experiencia', 'Ana Gómez', 'Sucursal Norte', '2025-09-30 17:00:00', '2025-09-30 18:00:00'),
  (18, 'Entrenamiento Funcional Outdoor', 'Clase de funcional al aire libre', 'Maximiliano López', 'Sucursal Sur', '2025-10-01 18:00:00', '2025-10-01 19:00:00'),
  (19, 'Kickboxing', 'Entrenamiento de artes marciales mixto con fitness', 'Martina Rossi', 'Sede Central', '2025-10-02 20:00:00', '2025-10-02 21:00:00'),
  (20, 'Stretch & Relax', 'Clase de estiramiento profundo y relajación', 'Claudia Vega', 'Sucursal Norte', '2025-10-03 08:00:00', '2025-10-03 09:00:00');
  

REPLACE INTO users (id, email, password, `name`, photo_url, `role`, validated)
VALUES
  (1, 'admin@root.com',
   '$2a$10$WMAB9OwhzQS8jCl8mhVHf.H0H43vWH80wNvesuaZM1kMHA2j0fNyK',
   'Admin User', '', 'ADMIN', true);
