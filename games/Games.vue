<template>
    <ion-content>
        <h2> Liste Games </h2>

        <ion-button size="default" @click="setOpenAdd(true)"> Ajout Games </ion-button>

        <ion-modal :is-open="isAddOpen">
            <ion-header>
                <ion-toolbar>
                    <ion-title> Ajouter Games </ion-title>
                    <ion-buttons slot="end">
                        <ion-button @click="setOpenAdd(false)"> Annuler </ion-button>
                    </ion-buttons>
                </ion-toolbar>
            </ion-header>
            <ion-content>
                <form @submit.prevent="handleSaveSubmit">
                    <ion-list>
                        	
			<ion-item>
				<ion-select label="console" label-placement="floating" name="console">
						
					<ion-select-option v-for="elt in dataconsole" :key="elt.id" :value="elt"> {{ elt.nomConsole }} </ion-select-option>
			
				</ion-select>
			</ion-item>
				<ion-item>
				<ion-label> Nom Jeu </ion-label>
				<ion-input type="text" name="nomJeu">	</ion-input>
			</ion-item>
	
                        <ion-item>
                            <ion-button size="default" color="tertiary" type="submit"> Save </ion-button>
                        </ion-item>
                    </ion-list>
                </form>
            </ion-content>
        </ion-modal>

        <ion-grid v-if="dataGames">
            <ion-row>
                			 <ion-col> Console </ion-col>
			 <ion-col> Nom Jeu </ion-col>
			 <ion-col> Id </ion-col>

            </ion-row>
            <ion-row v-for="item in dataGames" :key="item.id">
                		<ion-col> {{ item.console.nomConsole }} </ion-col>
		<ion-col> {{ item.nomJeu }} </ion-col>
		<ion-col> {{ item.id }} </ion-col>

                <ion-row>
                    <ion-col>
                        <ion-button size="small" color="tertiary" :key="item.id" @click="handleSelectItem(item.id)"> Modifier </ion-button>
                    </ion-col>
                    <ion-col>
                        <ion-button size="small" color="warning" :key="item.id" @click="handleDeleteSubmit(item)"> Supprimer </ion-button>
                    </ion-col>
                </ion-row>
            </ion-row>
        </ion-grid>

        <!------ pagination ------------>
        <ion-button v-if="currentPage > 1" @click="loadPreviousPage" size="default" color="light">
            Précédent
        </ion-button>
        <ion-button v-if="currentPage < totalPages" @click="loadNextPage" size="default" color="medium">
            Suivant
        </ion-button>

        <ion-modal :is-open="isOpen">
            <ion-header>
                <ion-toolbar>
                    <ion-title> Modification </ion-title>
                    <ion-buttons slot="end">
                        <ion-button @click="setOpen(false)"> Annuler </ion-button>
                    </ion-buttons>
                </ion-toolbar>
            </ion-header>
            <ion-content>
                <form @submit.prevent="handleUpdateSubmit">
                    <ion-list>
                        	
			<ion-item>
				<ion-select label="console" label-placement="floating" name="console">
					
			<ion-select-option v-for="elt in dataconsole" :key="elt.id" :value="elt"> {{ elt.nomConsole }} </ion-select-option>
	
	
				</ion-select>
			</ion-item><ion-item>
				<ion-label> Nom Jeu </ion-label>
				<ion-input type="text" name="nomJeu" :value="selectedItem ? selectedItem.nomJeu:''">
			</ion-input>
			</ion-item>
	<ion-item>
				<ion-label>  </ion-label>
				<ion-input type="hidden" name="id" :value="selectedItem ? selectedItem.id:''">
			</ion-input>
			</ion-item>
	
                        <ion-item>
                            <ion-button size="default" color="tertiary" type="submit">
                                Valider
                            </ion-button>
                        </ion-item>
                    </ion-list>
                </form>
            </ion-content>
        </ion-modal>
    </ion-content>
</template>

<script setup lang="ts">
    import {
        IonButtons,
        IonButton,
        IonModal,
        IonHeader,
        IonContent,
        IonToolbar,
        IonTitle,
        IonList,
        IonItem,
        IonLabel,
        IonInput,
        IonGrid,
        IonRow,
        IonCol,
        IonSelect,
        IonSelectOption
    } from '@ionic/vue';
    import axios from 'axios';

    import { ref, computed } from 'vue';
</script>

<script lang="ts">
/// modal / pop-up
    const isOpen = ref(false);
    const isAddOpen = ref(false);

    const setOpenAdd = (open: boolean) => {
        isAddOpen.value = open;
    }

    const setOpen = (open: boolean) => {
        isOpen.value = open;
    }

/// url back end 
    const url = 'http://localhost:8080/';

    	const loadingGames = ref(false);
	const dataGames = ref(null);
	 const currentPage = ref(1);
	 const pageSize = 5; 
	 const totalPages = computed(() => {
		if(!dataGames.value) return 0;
		return Math.ceil(dataGames.value.length / pageSize); }
		);
	const loadingConsole = ref(false);
	const dataConsole = ref([]);
	
	
    	const fetchGames = () => {
			loadingGames.value = true;
			axios.get(url + 'games' + '/paging?page=' + currentPage.value + '&size=' + pageSize)
			.then(response => {
				loadingGames.value = false;
				dataGames.value = response.data.data || response.data || [];
			})
	.catch(error => {
				loadingGames.value = false;
				error.value = error.message;
				});
					};
		fetchGames();
		const loadNextPage = () => {
			if (currentPage.value < totalPages.value) {
			currentPage.value++;
			fetchGames();
		}
		};
		const loadPreviousPage = () => {
			 if (currentPage.value > 1) {
			currentPage.value--;
			fetchGames();
		}
		};
	const fetchConsole = () => {
			loadingConsole.value = true;
			axios.get(url + 'console')
			.then(response => {
				loadingConsole.value = false;
				dataConsole.value = response.data.data || response.data || [];
			})
	.catch(error => {
				loadingConsole.value = false;
				error.value = error.message;
				});
					};
				fetchConsole();

/// select item
    const selectedItem = ref(null);
    
    const handleSelectItem = (itemKey) => {
        const itemDetails = dataGames._rawValue.find(item => item.id === itemKey);
        selectedItem.value = itemDetails;
        setOpen(true);
    }
    
/// save
    export const handleSaveSubmit = async (event: any) => {
        event.preventDefault();

        const form = event.target;
        const formData = new FormData(form);
        const data = {};
    
        for (let [key, value] of formData.entries()) {
            const element = form.elements[key];
            if(element.tagName === 'ION-SELECT') {
                const isMultiple = element.hasAttribute('multiple');
                if(isMultiple) {
                    data[key] = Array.from(value);
                } else {
                    data[key] = value;
                }
            } else {
                data[key] = value;
            }
        }

        try {
            const response = await fetch(url + 'games', {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
                    'Content-Type': 'application/json'
                }
            });
    
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            setOpenAdd(false);
        } catch (error) {
            console.log('Error:', error);
        }
    };

/// update
    export const handleUpdateSubmit = async (event: any) => {
        event.preventDefault();

        const form = event.target;
        const formData = new FormData(form);
        const data = {};
    
        for (let [key, value] of formData.entries()) {
            const element = form.elements[key];
            if(element.tagName === 'ION-SELECT') {
                const isMultiple = element.hasAttribute('multiple');
                if(isMultiple) {
                    data[key] = Array.from(value);
                } else {
                    data[key] = value;
                }
            } else {
                data[key] = value;
            }
        }

        try {
            const response = await fetch(url + 'games', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
                body: JSON.stringify(data)
            });
    
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            setOpen(false);
        } catch (error) {
            console.error('Error:', error);
        }
    };

/// delete
    const handleDeleteSubmit = async (item) => {
        try {
            console.log(item);
            const response = await fetch(url + 'games', {
                method: 'DELETE',
                headers: {
                'Content-Type': 'application/json',
                },
                body: JSON.stringify(item)
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };
</script>

<style>
    ion-row {
        border: 1px solid rgba(245, 245, 245, 0.256);
    }
</style>
