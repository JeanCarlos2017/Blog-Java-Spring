const {gql} = require ('apollo-server')

module.exports = gql `
type Tema{
    id: ID
    descricao: String
    postagens: [Postagem]
}
type Postagem{
    id: ID
    titulo: String
    texto: String
    temas: Tema
    favorite: Boolean
}
type Mutation{
    toggleFavoritePostagem(id: ID): Postagem
}
type Query{
    postagens(
        titulo: String
        texto: String
    ): [Postagem]
    postagemById(id: ID): Postagem
    temas(
      id: ID
      descricao: String  
    ): [Tema]
    temaById(id: ID): Tema
}
`