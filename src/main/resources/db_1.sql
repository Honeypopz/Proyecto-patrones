
-- Crear base de datos
DROP DATABASE IF EXISTS insumos_medicos;
CREATE DATABASE insumos_medicos;
USE insumos_medicos;

-- Crear usuario
DROP USER IF EXISTS 'juan'@'localhost';
CREATE USER 'juan'@'localhost' IDENTIFIED BY '117650456';
GRANT ALL PRIVILEGES ON insumos_medicos.* TO 'juan'@'localhost';
FLUSH PRIVILEGES;

-- ============================================
-- TABLAS
-- ============================================

-- usuarios
CREATE TABLE usuarios (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  rol ENUM('admin','vendedor','cliente') NOT NULL,
  telefono VARCHAR(20)
);

-- categorias
CREATE TABLE categorias (
  id_categoria INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT
);

-- productos
CREATE TABLE productos (
  id_producto INT AUTO_INCREMENT PRIMARY KEY,
  codigo VARCHAR(20) NOT NULL UNIQUE,
  nombre VARCHAR(200) NOT NULL,
  categoria_id INT,
  precio DECIMAL(10,2) NOT NULL,
  stock INT DEFAULT 0,
  FOREIGN KEY (categoria_id) REFERENCES categorias(id_categoria)
);

-- clientes
CREATE TABLE clientes (
  id_cliente INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(200) NOT NULL,
  direccion TEXT,
  telefono VARCHAR(20),
  email VARCHAR(100)
);

-- ventas
CREATE TABLE ventas (
  id_venta INT AUTO_INCREMENT PRIMARY KEY,
  numero_venta VARCHAR(20) NOT NULL UNIQUE,
  cliente_id INT,
  vendedor_id INT,
  fecha DATE,
  total DECIMAL(10,2),
  estado ENUM('pendiente','completada') DEFAULT 'completada',
  FOREIGN KEY (cliente_id) REFERENCES clientes(id_cliente),
  FOREIGN KEY (vendedor_id) REFERENCES usuarios(id_usuario)
);

-- detalle ventas
CREATE TABLE detalle_ventas (
  id_detalle INT AUTO_INCREMENT PRIMARY KEY,
  venta_id INT,
  producto_id INT,
  cantidad INT,
  precio_unitario DECIMAL(10,2),
  subtotal DECIMAL(10,2),
  FOREIGN KEY (venta_id) REFERENCES ventas(id_venta),
  FOREIGN KEY (producto_id) REFERENCES productos(id_producto)
);

-- ============================================
-- DATOS DE EJEMPLO
-- ============================================

-- Usuarios
INSERT INTO usuarios (nombre, email, password, rol, telefono) VALUES
('Administrador', 'admin@sistema.com', '123456', 'admin', '987654321'),
('María Vendedora', 'maria@sistema.com', '123456', 'vendedor', '987654322'),
('Juan Cliente', 'juan@email.com', '123456', 'cliente', '987654323');

-- Categorías
INSERT INTO categorias (nombre, descripcion) VALUES
('Protección Personal', 'Mascarillas, guantes, etc.'),
('Instrumental', 'Bisturís, pinzas, etc.'),
('Material de Curación', 'Vendas, gasas, etc.'),
('Equipos Médicos', 'Termómetros, oxímetros, etc.');

-- Productos (solo 6)
INSERT INTO productos (codigo, nombre, categoria_id, precio, stock) VALUES
('MED-001', 'Mascarilla N95', 1, 2.50, 500),
('MED-002', 'Guantes de Látex (caja)', 1, 15.00, 45),
('MED-003', 'Bisturí Desechable', 2, 0.75, 1000),
('MED-004', 'Venda Elástica 10cm', 3, 3.20, 150),
('MED-005', 'Termómetro Digital', 4, 25.00, 80),
('MED-006', 'Alcohol en Gel 500ml', 1, 5.50, 200);

-- Clientes
INSERT INTO clientes (nombre, direccion, telefono, email) VALUES
('Ana García', 'Av. Los Pinos 123, Lima', '987654324', 'ana@email.com'),
('Empresa Medical SAC','Av. Industrial 456, Lima', '987654325', 'ventas@empresa.com'),
('Hospital Central','Av. Salud 789, Lima', '987654326', 'compras@hospital.com');

-- Ventas
INSERT INTO ventas (numero_venta, cliente_id, vendedor_id, fecha, total, estado) VALUES
('V-001', 1, 2, '2024-03-01', 113.00, 'completada'),
('V-002', 2, 2, '2024-03-05', 531.00, 'completada'),
('V-003', 3, 2, '2024-03-12', 800.00, 'completada');

-- Detalle de ventas
INSERT INTO detalle_ventas (venta_id, producto_id, cantidad, precio_unitario, subtotal) VALUES
(1, 1, 20, 2.50, 50.00),
(1, 4, 10, 3.20, 32.00),
(2, 2, 10, 15.00, 150.00),
(2, 5, 12, 25.00, 300.00),
(3, 3, 100, 0.75, 75.00),
(3, 6, 50, 5.50, 275.00);

