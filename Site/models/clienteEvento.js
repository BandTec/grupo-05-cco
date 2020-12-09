/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Participar = sequelize.define('Participar', {
        id: {
            field: 'idClienteEventos',
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        parqueEvento: {
            field: 'fkParqueEventos',
            type: DataTypes.INTEGER,
            allowNull: false
        },
        cliente: {
            field: 'fkCliente',
            type: DataTypes.INTEGER,
            allowNull: false
        },
    },
     {
        tableName: 'clienteEventos',
        freezeTableName: true,
        underscored: true,
        timestamps: false,
    });

    return Participar;
};