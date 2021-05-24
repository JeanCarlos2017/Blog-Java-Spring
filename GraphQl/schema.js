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

input PostagemInput{
    titulo: String
    texto: String
    tema: TemaInput    
}

input TemaInput{
    id: ID
}
type Mutation{
    toggleFavoritePostagem(id: ID): Postagem
    addPostagem(postagem: PostagemInput): Postagem
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