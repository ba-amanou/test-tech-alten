import { Injectable, inject, signal } from "@angular/core";
import { Cart, CartItem } from "./cart.model";
import { HttpClient } from "@angular/common/http";
import { catchError, Observable, of, tap } from "rxjs";
import { Product } from "app/products/data-access/product.model";
import { environment } from "environments/environment";

@Injectable({
    providedIn: "root"
}) export class CartService {

    private readonly http = inject(HttpClient);
    private readonly path = `${environment.apiUrl}/cart`
    
    private readonly _cart = signal<Cart>( {items : []} );

    public readonly cart = this._cart.asReadonly();

    public getCart(): Observable<Cart> {
        return this.http.get<Cart>(this.path).pipe(
            tap((cart) => this._cart.set(cart)),
        );
    }

    public addCartItem(product : Product): Observable<CartItem> {
        return this.http.post<CartItem>(this.path+'/items', {id:product.id,quantity:1}).pipe(
            tap(() => {
                //verifier si le produit existe déjà
                const existingItem = this._cart().items.find(i => i.product.id === product.id);
                
                if(existingItem){
                    const updatedItem = {...existingItem, quantity : existingItem.quantity + 1 };
                    this._cart.update(cart => ({
                            ...cart,
                            items: cart.items.map( item => 
                                item.product.id === updatedItem.product.id ? updatedItem :item )
                        })
                        
                    );  
                } else{
                    const cartItem : CartItem = {
                        product,
                        quantity : 1
                    }
                    this._cart.update(cart => ({
                            ...cart,
                            items: [...cart.items, cartItem]
                        })
                    );                                   
                }
            }), 
        );
    }

    public updateCartItemQuantity(item: CartItem): Observable<CartItem> {
        return this.http.patch<CartItem>(`${this.path}/items/${item.product.id}`, { quantity: item.quantity }).pipe(
            tap(() => this._cart.update(cart => ({
                ...cart,
                items : cart.items.map(cartItem => item.product.id === cartItem.product.id? {...cartItem, quantity:item.quantity}: cartItem)
                })
            ))
        )
    };
        

    public deleteCartItem(productId: number): Observable<void> {
        return this.http.delete<void>(`${this.path}/items/${productId}`).pipe(
            tap(() => this._cart.update(cart => ({
                ...cart,
                items : cart.items.filter(item => item.product.id !== productId)
                })
            ))
        );
    } 
}