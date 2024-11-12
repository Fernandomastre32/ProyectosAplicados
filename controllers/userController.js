const User = require('../models/userModel');
const cloudinary = require('cloudinary').v2;
const multer = require('multer');

// Configurar Multer para manejar archivos en memoria
const storage = multer.memoryStorage();
const upload = multer({ storage: storage });

// Configurar Cloudinary
cloudinary.config({
    cloud_name: process.env.CLOUDINARY_CLOUD_NAME,
    api_key: process.env.CLOUDINARY_API_KEY,
    api_secret: process.env.CLOUDINARY_API_SECRET
});

class UserController {
    static async getAllUsers(req, res) {
        try {
            const users = await User.findAll();
            res.json(users);
        } catch (error) {
            res.status(500).json({ error: error.message });
        }
    }

    static async createUser(req, res) {
        try {
            const { name, email } = req.body;

            // Verifica si hay un archivo (imagen) en la solicitud
            if (req.file) {
                const uploadImage = async (buffer) => {
                    const options = {
                        use_filename: true,
                        unique_filename: false,
                        overwrite: true,
                    };

                    try {
                        // Subir la imagen desde el buffer (almacenado en memoria)
                        const result = await new Promise((resolve, reject) => {
                            cloudinary.uploader.upload_stream(options, (error, result) => {
                                if (error) return reject(error);
                                resolve(result);
                            }).end(buffer);
                        });

                        return result.public_id;
                    } catch (error) {
                        console.error(error);
                    }
                };

                const imageBuffer = req.file.buffer;  // Obtener el buffer del archivo
                const id_cloudinary = await uploadImage(imageBuffer);
                console.log(id_cloudinary);

                // Guardar el usuario en la base de datos
                const user = await User.create({ name, email, imagen: id_cloudinary });
                res.status(201).json(user);
            } else {
                res.status(400).json({ message: 'No se ha subido ninguna imagen.' });
            }

        } catch (error) {
            res.status(500).json({ error: error.message });
        }
    }

    static async getUserById(req, res) {
        try {
            const user = await User.findById(req.params.id);
            if (!user) {
                return res.status(404).json({ message: 'Usuario no encontrado!' });
            }
            res.json(user);
        } catch (error) {
            res.status(500).json({ error: error.message });
        }
    }

    static async updateUser(req, res) {
        try {
            const user = await User.update(req.params.id, req.body);
            if (!user) {
                return res.status(404).json({ message: 'Usuario no encontrado!' });
            }
            res.json(user);
        } catch (error) {
            res.status(500).json({ error: error.message });
        }
    }

    static async deleteUser(req, res) {
        try {
            const result = await User.delete(req.params.id);
            res.json(result);
        } catch (error) {
            res.status(500).json({ error: error.message });
        }
    }
}

module.exports = { UserController, upload };
