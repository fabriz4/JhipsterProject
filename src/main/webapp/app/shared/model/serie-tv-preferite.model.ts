export interface ISerie_tv_preferite {
  id?: number;
  user?: string;
  name?: string;
  overview?: string;
  popularity?: string;
  avg_vote?: number;
}

export class Serie_tv_preferite implements ISerie_tv_preferite {
  constructor(
    public id?: number,
    public user?: string,
    public name?: string,
    public overview?: string,
    public popularity?: string,
    public avg_vote?: number
  ) {}
}
