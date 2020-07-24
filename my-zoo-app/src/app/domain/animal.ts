import { AnimalType } from "./animaltype";

export class Animal{ 
    id: number;
    sex: string;
    type: string;
    name: string;
    notes: string;
    food: string;

    constructor(
        id: number,
        sex: string,
        type: string,
        name: string,
        notes: string,
        food: string
    ){
        this.id = id;
        this.sex = sex;
        this.type = type;
        this.name = name;
        this.notes = notes;
        this.food = food;
    }
}