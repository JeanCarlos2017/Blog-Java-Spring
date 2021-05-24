const {RESTDataSource} = require('apollo-datasource-rest')
const _ = require('lodash');
const token = require('./token')

class PostagemAPI extends RESTDataSource{
    constructor(config){
        super();
        this.baseURL= 'http://localhost:8080/postagem';
    }

     async getAllPostagem(){
        const data = await this.get('/');
        return data;
    }

    async getPostagemById(id){
        console.log(id)
        const data = await this.get(`/${id}`, token)
        return data;
    }
}

module.exports = PostagemAPI;