
package Backend;


public class createbd {
    /*
    CREATE DATABASE DbDio
    GO
    
    USE DbDio
    GO
    
    CREATE TABLE [dbo].[TblClients] (
    [Dni]       VARCHAR (50) PRIMARY KEY,
    [Nombre]    VARCHAR (50) DEFAULT (NULL) NULL,
    [Apellido]  VARCHAR (50) DEFAULT (NULL) NULL,
    [Correo]    VARCHAR (50) DEFAULT (NULL) NULL,
    [Direccion] VARCHAR (50) DEFAULT (NULL) NULL,
    [Telefono]  VARCHAR (50) DEFAULT (NULL) NULL,
    [Fecha]     VARCHAR (50) DEFAULT (NULL) NULL,

);
    GO

CREATE TABLE [dbo].[TblCompras] (
    [Factura]  INT  PRIMARY KEY,
    [Codigo]   VARCHAR (50) NOT NULL,
    [Cantidad] INT          NOT NULL,
    [ValorU]   FLOAT (53)   NOT NULL,
    [ValorT]   FLOAT (53)   NOT NULL,

);
    GO

CREATE TABLE [dbo].[TblFactC] (
    [Fact]    INT    IDENTITY (1, 1) PRIMARY KEY,
    [Nit]     VARCHAR (50) NOT NULL,
    [Nombre]  VARCHAR (50) NOT NULL,
    [Total]   FLOAT (53)   NOT NULL,
    [Fecha]   DATETIME     DEFAULT (getdate()) NULL

);
    GO

CREATE TABLE [dbo].[TblFactV] (
    [Fact]    INT          IDENTITY (1, 1)PRIMARY KEY ,
	[DNI] VARCHAR (100) DEFAULT (NULL) NULL,
    [Total]  FLOAT (53)    DEFAULT (NULL) NULL,
    [Fecha]  DATETIME      DEFAULT (getdate()) NULL

);
    GO

CREATE TABLE [dbo].[TblLogin] (
    [Login] INT          IDENTITY (1, 1) PRIMARY KEY ,
    [Useer] VARCHAR (50) NOT NULL,
    [Rol]   VARCHAR (50) NOT NULL,
    [Fecha] DATETIME     DEFAULT (getdate()) NULL


);
    GO

CREATE TABLE [dbo].[TblProducts] (
    [Codigo]    VARCHAR (50) PRIMARY KEY,
    [Nombre]    VARCHAR (50) DEFAULT (NULL) NULL,
    [Apodo]     VARCHAR (50) DEFAULT (NULL) NULL,
    [CantidadP] INT          DEFAULT (NULL) NULL,
    [CantidadV] INT          DEFAULT (NULL) NULL,
    [ValorV]    FLOAT (53)   DEFAULT (NULL) NULL,
    [ValorC]    FLOAT (53)   DEFAULT (NULL) NULL,
    [ValorD]    FLOAT (53)   DEFAULT (NULL) NULL

);
    GO

CREATE TABLE [dbo].[TblProv] (
    [Nit]      VARCHAR (50)  PRIMARY KEY,
    [Empresa]  VARCHAR (50)  DEFAULT (NULL) NULL,
    [Telefono] VARCHAR (50)  DEFAULT (NULL) NULL,
    [Dias]     VARCHAR (100) DEFAULT (NULL) NULL,
    [CantiD]   INT           DEFAULT (NULL) NULL,
    [PorceD]   INT           DEFAULT (NULL) NULL

);
    GO

CREATE TABLE [dbo].[TblUsers] (
    [Useer] VARCHAR (30) PRIMARY KEY,
    [Pass]  VARCHAR (30) NOT NULL,
    [Rol]   VARCHAR (30) NOT NULL
);
    GO

CREATE TABLE [dbo].[TblVentas] (
    [Factura]   VARCHAR (50) PRIMARY KEY,
    [Codigo]    VARCHAR (50) DEFAULT (NULL) NULL,
    [CantidadP] INT          DEFAULT (NULL) NULL,
    [ValorU]    FLOAT (53)   DEFAULT (NULL) NULL,
    [ValorT]    FLOAT (53)   DEFAULT (NULL) NULL,
);
    GO

INSERT INTO TblUsers (Useer, Pass, Rol) VALUES
('ADMIN', 'TRABA-JO', 'ADMIN'),
('EDWIN', '0000', 'CAJERO');



*/
    
    
}
