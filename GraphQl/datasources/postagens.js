const {RESTDataSource} = require('apollo-datasource-rest')
const _ = require('lodash');
const token = require('./token')

class PostagemAPI extends RESTDataSource{
    constructor(config){
        super();
        this.baseURL= 'http://localhost:8080/postagem';
    }

     async getAllPostagem(args){
        const data = await this.get('/');
        const dataFilter= _.filter(data, args);
        return dataFilter;
    }

    async getPostagemById(id){
        const data = await this.get(`/${id}`, token)
        return data;
    }

    async toggleFavoritePostagem(id){
        const data = await this.get(`/${id}`, token);
        data.favorite = !data.favorite;
        return data;
    }
}

module.exports = PostagemAPI;