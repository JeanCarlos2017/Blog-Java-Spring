const {RESTDataSource} = require('apollo-datasource-rest')
const _ = require('lodash');
const token = require('./token')

class TemaAPI extends RESTDataSource{
    constructor(config){
        super();
        this.baseURL= 'http://localhost:8080/tema';
    }

     async getTemas(){
        const data = await this.get('/');
        return data;
    }

    async getTemaById(id){
        const data = await this.get(`/${id}`, token)
        return data;
    }
}

module.exports = TemaAPI;