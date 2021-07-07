import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'srm-msg-list',
        loadChildren: () => import('./srm-msg-list/srm-msg-list.module').then(m => m.JhipsterSampleApplicationSrmMsgListModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class JhipsterSampleApplicationEntityModule {}
