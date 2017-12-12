import { Injectable } from '@angular/core';
import {Hero} from '../_models/Hero';
import {Monster} from '../_models/Monster';
@Injectable()
export class ValidStepService {
  blocks: string[] = ['bog', 'lava', 'rock', 'stoneWall', 'horizontalWall', 'verticalWall', 'water'];
  constructor() {}
  validStep(i: number, hero: Hero, board: Monster[]) {
    console.log('koordinateavalid:');
    console.log(this.coordinateCorrect(i, hero));
    console.log('stepblockvalid:');
    console.log(this.blockInStep(i, board));
    if (this.coordinateCorrect(i, hero) && this.blockInStep(i, board)) {return true; }else {return false; }
  }
  private coordinateCorrect(i: number, hero: Hero) {
      if (hero.position + 1 === i || hero.position - 1 === i
        || hero.position + 10 === i || hero.position - 10 === i  ) {
        return true;
  }else {return false; }
}
  private blockInStep(i: number, board: Monster[]) {
    if (this.blocks.indexOf(board[i].name) > -1) {
      return false;
    }else { return true; }
  }
}
