const {gql} = require ('apollo-server')

module.exports = gql `
type Tema{
    id: ID
    descricao: String
}
type Postagem{
    id: ID
    titulo: String
    texto: String
}
type Query{
    postagens: [Postagem]
    postagemById(id: ID): Postagem
    temas: [Tema]
    temaById(id: ID): Tema
}
`