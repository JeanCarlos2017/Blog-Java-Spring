
module.exports = {
    toggleFavoritePostagem: (parent, { id }, {dataSources}, info) =>{
        return dataSources.postagemAPI.toggleFavoritePostagem(id);
    },
    addPostagem: (parent, { postagem }, {dataSources}, info) =>{
        return dataSources.postagemAPI.addPostagem(postagem);
    }
}