const { Pool } = require('pg');

const pool = new Pool({
  host: process.env.DATABASE_HOST || 'postgres_container', // Cambiar 'db' a 'postgres_container'
  user: process.env.DATABASE_USER || 'postgres',
  password: process.env.DATABASE_PASSWORD || '123456789',
  database: process.env.DATABASE_NAME || 'Producto',
  port: 5432,
});

// Intento de conexión para verificar si hay algún problema
pool.connect((err, client, release) => {
  if (err) {
    console.error('No se pudo conectar a la base de datos:', err.stack);
  } else {
    console.log('Conexión exitosa a PostgreSQL');
    release();
  }
});

module.exports = pool;
