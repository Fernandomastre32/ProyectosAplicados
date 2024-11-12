// index.js
const express = require('express');
const { Pool } = require('pg');
const userRoutes = require('./routes/userRoutes');
const swaggerDocs = require('./config/swagger');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 3000;

// Configuración de la conexión a PostgreSQL
const pool = new Pool({
  host: process.env.DATABASE_HOST || 'postgres_container',
  user: process.env.DATABASE_USER || 'postgres',
  password: process.env.DATABASE_PASSWORD || '123456789',
  database: process.env.DATABASE_NAME || 'Producto',
  port: 5432, // Puerto predeterminado de PostgreSQL
});

// Middleware para Swagger
swaggerDocs(app);

// Middleware para parseo de JSON
app.use(express.json());

// Verificar la conexión a la base de datos
pool.connect((err) => {
  if (err) {
    console.error('Error al conectar con la base de datos:', err);
  } else {
    console.log('Conexión exitosa a la base de datos PostgreSQL');
  }
});

// Rutas de tu API
app.use('/api', userRoutes);

// Iniciar el servidor
app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT}`);
});

module.exports = { app, pool };
