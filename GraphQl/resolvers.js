const Query = require('./resolvers/query');
const PostagemResolver= require('./resolvers/postagem-resolver');
const TemaResolver= require('./resolvers/tema-resolver')


module.exports = {
    Query,
    Postagem: PostagemResolver,
    Tema: TemaResolver
};