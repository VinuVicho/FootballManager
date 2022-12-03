import { Player } from "../player/player";

export interface Team {
    id: number;
    money: number;
    commission: number;
    name: string;
    logo: string;
    city: string;
    country: string;
    about: string;
    players: Player[];
}