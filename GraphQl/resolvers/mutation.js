module.exports = {
    toggleFavoritePostagem: (parent, { id }, {dataSources}, info) =>{
        return dataSources.postagemAPI.toggleFavoritePostagem(id);
    },
}