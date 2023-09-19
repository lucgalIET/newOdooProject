import { Column } from "./column.model";

export class Project{
    constructor(public columns: Column[]){}
}