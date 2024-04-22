import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';

const routes: Array<RouteRecordRaw> = [
  	{ 
			 path: '/console', 
			 component: () => import ('@/views/console/Console.vue') 
		},
	{ 
			 path: '/games', 
			 component: () => import ('@/views/games/Games.vue') 
		},
	
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router;
