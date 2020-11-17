/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Eventos = sequelize.define('Eventos', {
        id: {
            field: 'idParqueEventos',
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        fkParque: {
            field: 'fkParque',
            type: DataTypes.INTEGER,
            allowNull: false
        },
        img_evento: {
            field: 'img_parque',
            type: DataTypes.STRING,
            allowNull: false
        },
        tituloEvento: {
            field: 'tituloEvento',
            type: DataTypes.STRING,
            allowNull: false
        },
        descricao: {
            field: 'descricao',
            type: DataTypes.STRING,
            allowNull: false
        },
        dataEventos: {
            field: 'dataEventos',
            type: DataTypes.STRING,
            allowNull: false
        }
    }, {
        tableName: 'parqueEventos',
        freezeTableName: true,
        underscored: true,
        timestamps: false,
    });

    return Eventos;
};