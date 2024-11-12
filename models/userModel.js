const pool = require('../config/db');

class User {
    static async findAll() {
        const result = await pool.query('SELECT * FROM users');
        return result.rows;
    }

    static async create(data) {
        const { name, email, imagen } = data;
        const result = await pool.query(
            'INSERT INTO users (name, email, imagen) VALUES ($1, $2, $3) RETURNING *',
            [name, email, imagen]
        );
        return result.rows[0];
    }

    static async findById(id) {
        const result = await pool.query('SELECT * FROM users WHERE id = $1', [id]);
        return result.rows[0];
    }

    static async update(id, data) {
        const { name, email } = data;
        const result = await pool.query(
            'UPDATE users SET name = $1, email = $2 WHERE id = $3 RETURNING *',
            [name, email, id]
        );
        return result.rows[0];
    }

    static async delete(id) {
        await pool.query('DELETE FROM users WHERE id = $1', [id]);
        return { message: 'Usuario eliminado exitosamente' };
    }
}

module.exports = User;
