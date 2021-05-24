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
    temas: Tema
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