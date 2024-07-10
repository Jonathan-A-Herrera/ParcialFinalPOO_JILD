CREATE DATABASE PARCIALFINAL
USE PARCIALFINAL
CREATE TABLE Cliente ( 
    ID_Cliente INT PRIMARY KEY IDENTITY,
    Nombre VARCHAR(100) NOT NULL,
    Dirección VARCHAR(200),
    Teléfono VARCHAR(15) );


CREATE TABLE Tarjeta ( 
    Número_Tarjeta VARCHAR(20) PRIMARY KEY,
    Fecha_Expiración DATE NOT NULL,
    Tipo VARCHAR(50) NOT NULL,
    Facilitador VARCHAR(50) NOT NULL,
    ID_Cliente INT, FOREIGN KEY (ID_Cliente) REFERENCES Cliente(ID_Cliente) );

CREATE TABLE Transacción ( 
    ID_Transacción INT PRIMARY KEY IDENTITY,
    Fecha_Compra DATE NOT NULL,
    Monto_Total DECIMAL(10, 2) NOT NULL,
    Descripción VARCHAR(255),
    Número_Tarjeta VARCHAR(20),
    FOREIGN KEY (Número_Tarjeta) REFERENCES Tarjeta(Número_Tarjeta) );

INSERT INTO Cliente (Nombre, Dirección, Teléfono) VALUES 
('Isaac Cañas', 'Calle 123', '71004100'),
('Luis Duque', 'Calle 456', '74933394'),
('Jonathan Herrera', 'Calle 789', '71339484'),
('Diego Contreras', 'Calle 987', '78123393'),
('Diana Santamaria', 'Calle 654', '73012384'),
('Iris Larin', 'Calle 737', '71234567'),
('Peter Parker', 'Avenida 90', '71234568'),
('Miles Morales', 'Avenida 777', '71288569'),
('Michael Scott', 'Avenida 12345', '71277570'),
('Joe Goldberg', 'Avenida 89', '71234500');
GO

INSERT INTO Tarjeta (Número_Tarjeta, Fecha_Expiración, Tipo, Facilitador, ID_Cliente) VALUES 
('4177582568099176', '2028-10-31', 'Crédito', 'MasterCard', 1),
('4177587677539087', '2029-11-30', 'Débito', 'Visa', 2),
('4177581118975091', '2026-01-04', 'Crédito', 'AmericanExpress', 3),
('4177589299867375', '2023-09-10', 'Débito', 'Visa', 4),
('4177582378995159', '2025-06-20', 'Crédito', 'MasterCard', 5),
('4177583334556677', '2027-08-15', 'Débito', 'MasterCard', 6),
('4177584445667788', '2028-02-28', 'Crédito', 'Visa', 7),
('4177585556778899', '2026-12-25', 'Débito', 'AmericanExpress', 8),
('4177586667889900', '2029-11-05', 'Crédito', 'Visa', 9),
('4177587778990011', '2025-03-30', 'Débito', 'MasterCard', 10);
GO


INSERT INTO Transacción (Fecha_Compra, Monto_Total, Descripción, Número_Tarjeta) VALUES 
('2024-01-01', 150.00, 'Compra en tienda', '4177582568099176'),
('2024-02-01', 200.00, 'Pago de servicios', '4177582568099176'),
('2024-03-01', 250.00, 'Compras en línea', '4177582568099176'),
('2024-04-01', 100.00, 'Cena en restaurante', '4177582568099176'),
('2024-05-01', 300.00, 'Pago de alquiler', '4177582568099176'),
('2024-06-01', 50.00, 'Cine', '4177582568099176'),
('2024-01-15', 120.00, 'Gasolina', '4177587677539087'),
('2024-02-15', 180.00, 'Supermercado', '4177587677539087'),
('2024-03-15', 220.00, 'Compra de ropa', '4177587677539087'),
('2024-04-15', 90.00, 'Café', '4177587677539087'),
('2024-05-15', 160.00, 'Libros', '4177587677539087'),
('2024-06-15', 150.00, 'Impuestos', '4177587677539087'),
('2024-01-10', 110.00, 'Farmacia', '4177581118975091'),
('2024-02-10', 130.00, 'Dentista', '4177581118975091'),
('2024-03-10', 140.00, 'Tienda de electrónicos', '4177581118975091'),
('2024-04-10', 170.00, 'Peluquería', '4177581118975091'),
('2024-05-10', 190.00, 'Mecánico', '4177581118975091'),
('2024-06-10', 20.00, 'Recarga de celular', '4177581118975091'),
('2024-01-20', 100.00, 'Veterinario', '4177589299867375'),
('2024-02-20', 200.00, 'Juguetería', '4177589299867375'),
('2024-03-20', 300.00, 'Mueblería', '4177589299867375'),
('2024-04-20', 400.00, 'Electrodomésticos', '4177589299867375'),
('2024-05-20', 500.00, 'Vacaciones', '4177589299867375'),
('2024-06-25', 500.00, 'Compra de refrigerador', '4177589299867375'),
('2024-02-05', 220.00, 'Hospital', '4177582378995159'),
('2024-03-05', 210.00, 'Concierto', '4177582378995159'),
('2024-04-05', 230.00, 'Tienda de mascotas', '4177582378995159'),
('2024-05-05', 240.00, 'Joyería', '4177582378995159'),
('2024-06-05', 250.00, 'Deportes', '4177582378995159'),
('2024-07-04', 50.00, 'Gasolina', '4177582378995159'),
('2023-01-05', 200.00, 'Ropa', '4177583334556677'),
('2023-02-05', 250.00, 'Calzado', '4177583334556677'),
('2023-03-05', 300.00, 'Juguetes', '4177583334556677'),
('2023-04-05', 100.00, 'Electrodomésticos', '4177583334556677'),
('2023-05-05', 150.00, 'Ropa deportiva', '4177583334556677'),
('2023-06-05', 350.00, 'Perfumes', '4177583334556677'),
('2023-02-10', 100.00, 'Restaurante', '4177584445667788'),
('2023-03-10', 200.00, 'Supermercado', '4177584445667788'),
('2023-04-10', 300.00, 'Ropa', '4177584445667788'),
('2023-05-10', 400.00, 'Calzado', '4177584445667788'),
('2023-06-10', 500.00, 'Muebles', '4177584445667788'),
('2023-07-10', 600.00, 'Viaje', '4177584445667788'),
('2022-01-15', 50.00, 'Café', '4177585556778899'),
('2022-02-15', 150.00, 'Supermercado', '4177585556778899'),
('2022-03-15', 250.00, 'Farmacia', '4177585556778899'),
('2022-04-15', 350.00, 'Electrónica', '4177585556778899'),
('2022-05-15', 450.00, 'Juguetería', '4177585556778899'),
('2022-06-15', 550.00, 'Ropa', '4177585556778899'),
('2021-01-20', 300.00, 'Alquiler', '4177586667889900'),
('2021-02-20', 400.00, 'Servicios', '4177586667889900'),
('2021-03-20', 500.00, 'Viaje', '4177586667889900'),
('2021-04-20', 600.00, 'Restaurante', '4177586667889900'),
('2021-05-20', 700.00, 'Muebles', '4177586667889900'),
('2021-06-20', 800.00, 'Electrodomésticos', '4177586667889900'),
('2020-01-25', 50.00, 'Gasolina', '4177587778990011'),
('2020-02-25', 100.00, 'Peaje', '4177587778990011'),
('2020-03-25', 150.00, 'Tienda', '4177587778990011'),
('2020-04-25', 200.00, 'Supermercado', '4177587778990011'),
('2020-05-25', 250.00, 'Restaurante', '4177587778990011'),
('2020-06-25', 300.00, 'Ropa', '4177587778990011');
GO


SELECT cl.ID_Cliente, cl.Nombre, trns.Fecha_Compra as 'Fecha de compra' from Cliente cl
inner join Tarjeta trj ON trj.ID_Cliente = cl.ID_Cliente
inner join Transacción trns ON trns.Número_Tarjeta = trj.Número_Tarjeta
where cl.ID_Cliente = 1 AND
trns.Fecha_Compra BETWEEN '2024-06-25' and '2024-07-1' 
order by trns.Fecha_Compra

SELECT 
    SUM(trns.Monto_Total) as 'Monto total', 
    trns.Número_Tarjeta, 
    COUNT(trns.ID_Transacción) as 'Cantidad de compras' 
FROM 
    Transacción trns
    INNER JOIN tarjeta trj ON trj.Número_Tarjeta = trns.Número_Tarjeta
    INNER JOIN Cliente cl ON cl.ID_Cliente = trj.ID_Cliente
WHERE 
    cl.ID_Cliente = 1
GROUP BY 
    trns.Número_Tarjeta;

drop table Transacción
drop table Tarjeta
drop table Cliente